package com.rslakra.microservice.yatrasuite.yatrathymeleaf.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.devamatre.appsuite.core.BeanUtils;
import com.devamatre.appsuite.core.Payload;
import com.devamatre.appsuite.core.enums.EntityStatus;
import com.devamatre.appsuite.spring.controller.web.AbstractWebController;
import com.devamatre.appsuite.spring.exception.InvalidRequestException;
import com.devamatre.appsuite.spring.filter.Filter;
import com.devamatre.appsuite.spring.parser.Parser;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.account.User;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * REST Controller to manage user activities
 */

@Controller
@RequestMapping("${uiPrefix}/users")
public class UserController extends AbstractWebController<User, Long> {

    private final Logger LOGGER = LogManager.getLogger(UserController.class);

    public static final String VIEW_USERS = "views/user/listUsers";
    public static final String REDIRECT_VIEW_USERS = "redirect:/ui/users";
    public static final String VIEW_USER_FORM = "views/user/editUser";
    public static final String VIEW_USER_DELETE = "views/user/deleteUser";
    public static final String VIEW_UPLOAD_USERS = "views/user/uploadUser";
    public static final String MODEL_ATTR_USERS = "users";
    public static final String MODEL_ATTR_USER = "user";
    public static final String FRAGMENT_FORM = " :: form";
    public static final String SECTION_USERS = "users";
    public static final String ID = "id";
    public static final String PATH_ID = "/{id}";
    public static final String X_REQUESTED_WITH_XML_HTTP_REQUEST = "X-Requested-With=XMLHttpRequest";

    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Saves the <code>T</code> object.
     *
     * @param user
     * @return
     */
    @PostMapping({"/", "/save"})
    @Override
    public String save(@RequestBody @Validated @ModelAttribute("user") User user) {
        if (BeanUtils.isNotNull(user.getId())) {
            User oldUser = userService.getById(user.getId());
            BeanUtils.copyProperties(user, oldUser);
            user = userService.update(oldUser);
        } else {
            if (BeanUtils.isEmpty(user.getPassword())) {
                user.setPassword(user.getEmail());
            }
            user = userService.create(user);
        }

        return REDIRECT_VIEW_USERS;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @param model
     * @return
     */
    @GetMapping
    @Override
    public String getAll(Model model) {
        LOGGER.debug("+getAll()");
        LOGGER.info("[GET] /ui/users");
        List<User> users = userService.getAll();
        model.addAttribute(MODEL_ATTR_USERS, users);
        LOGGER.debug("-getAll(), listUsers:{}", VIEW_USERS);
        return VIEW_USERS;
    }

    /**
     * Filters the list of <code>T</code> objects.
     *
     * @param model
     * @param filter
     * @return
     */
    @Override
    public String filter(Model model, Filter filter) {
        return null;
    }

    /**
     * @param model
     * @param allParams
     * @return
     */
    @Override
    public String filter(Model model, @RequestParam Map<String, Object> allParams) {
        return null;
    }

    /**
     * Displays Create/Update object page.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping(path = {"/create", PATH_ID + "/update"})
    @Override
    public String editObject(Model model, @PathVariable(value = ID, required = false) Long id) {
        LOGGER.debug("+editObject({}, {})", model, id);
        User user = null;
        if (BeanUtils.isNotNull(id)) {
            user = userService.getById(id);
            if (BeanUtils.isNull(user)) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        } else {
            user = new User();
        }
        model.addAttribute(MODEL_ATTR_USER, user);
        LOGGER.debug("-editObject(), editObject:{}, user:{}", VIEW_USER_FORM, user);
        return VIEW_USER_FORM;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID, headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String editObjectPartially(Model model, @PathVariable(ID) Long id) {
        LOGGER.info("Requesting user={} via XHR request.", id);
        // Let Thymeleaf only return the th:fragment="form" within the view
        return editObject(model, id) + FRAGMENT_FORM;
    }

    /**
     * @param id
     * @param paramName
     * @param paramValue
     */
    @PostMapping(headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST}, params = {"pk"})
    @ResponseStatus(code = NO_CONTENT)
    public void saveObjectPartially(@RequestParam("pk") Long id, @RequestParam("name") String paramName,
                                    @RequestParam("value") String paramValue) {
        LOGGER.debug("+saveObjectPartially({}, {}, {})", id, paramName, paramValue);
        User user = userService.getById(id);
        if (BeanUtils.isNull(user)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        // validate the param key/value and edit it.
        if ("email".equalsIgnoreCase(paramName)) {
            user.setEmail(paramValue);
        } else if ("firstName".equalsIgnoreCase(paramName)) {
            user.setFirstName(paramValue);
        } else if ("middleName".equalsIgnoreCase(paramName)) {
            user.setMiddleName(paramValue);
        } else if ("lastName".equalsIgnoreCase(paramName)) {
            user.setLastName(paramValue);
        } else if ("status".equalsIgnoreCase(paramName)) {
            user.setStatus(EntityStatus.ofString(paramValue));
        } else {
            LOGGER.warn("Invalid User's Update Request! Parameter name=[{}], value=[{}]", paramName, paramValue);
            return;
        }

        user = userService.update(user);
        LOGGER.debug("-saveObjectPartially(), user:{}", user);
    }

    /**
     * Displays Delete User Page.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping({"/delete/{id}", PATH_ID + "/delete"})
    public String showDeletePage(Model model, @PathVariable(ID) String id) {
        LOGGER.debug("+showDeletePage({}, {})", model, id);
        User user = userService.getById(BeanUtils.asType(id, Long.class));
        if (BeanUtils.isNull(user)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute(MODEL_ATTR_USER, user);
        LOGGER.debug("-showDeletePage(), deleteUserPage:{}", VIEW_USER_DELETE);
        return VIEW_USER_DELETE;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID + "/delete", headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String showDeleteFormPage(Model model, @PathVariable(ID) String id) {
        LOGGER.info("Requesting delete user={} via XHR request.", id);
        // Let Thymeleaf only return the th:fragment="form" within the view
        return showDeletePage(model, id) + FRAGMENT_FORM;
    }

    /**
     * Gets a user.
     *
     * @param email the email of the user to retrieve
     * @return Json with the details about the user
     * @throws NotFoundException if the email does not exist
     */
    @GetMapping("/{email}/profile")
    public ResponseEntity<User> getProfile(@PathVariable String email) throws NotFoundException {
        LOGGER.info("[GET] /ui/users/{email}");
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @PostMapping({"/delete/{id}", PATH_ID + "/delete"})
    @Override
    public String delete(Model model, @PathVariable(ID) Long id) {
        LOGGER.debug("+delete({}, {})", model, id);
        if (BeanUtils.isEmpty(id)) {
            throw new InvalidRequestException("The ID should provide!");
        }
        userService.delete(id);
        LOGGER.debug("-delete(), redirectView:{}", REDIRECT_VIEW_USERS);
        return REDIRECT_VIEW_USERS;
    }

    /**
     * Deletes a user.
     *
     * @param email the email of the user to delete
     * @return a message indicated the user was deleted
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<User> deleteUser(@PathVariable String email) {
        LOGGER.debug("+delete({})", email);
        LOGGER.info("[DELETE] /ui/users/{email}");
        User user = userService.getByEmail(email);
        userService.delete(user.getId());
        LOGGER.debug("-delete(), user:{}", user);
        return ResponseEntity.ok(user);
    }

    /**
     * @return
     */
    @Override
    public Parser<User> getParser() {
        return null;
    }

    /**
     * Displays the upload <code>Users</code> UI.
     *
     * @return
     */
    @GetMapping(path = {"/upload"})
    public String showUploadPage() {
        return VIEW_UPLOAD_USERS;
    }

    /**
     * Uploads the file of <code>Users</code>.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
//        try {
//            List<User> users = null;
//            UserParser userParser = new UserParser();
//            if (CsvParser.isCSVFile(file)) {
//                users = userParser.readCSVStream(file.getInputStream());
//            } else if (ExcelParser.isExcelFile(file)) {
//                users = userParser.readStream(file.getInputStream());
//            }
//
//            // check the task list is available
//            if (Objects.nonNull(users)) {
//                users = userService.create(users);
//                LOGGER.debug("users: {}", users);
//                payload.withMessage("Uploaded the file '%s' successfully!", file.getOriginalFilename());
//                return ResponseEntity.status(HttpStatus.OK).body(payload);
//            }
//        } catch (Exception ex) {
//            LOGGER.error("Could not upload the file:{}!", file.getOriginalFilename(), ex);
//            payload.withMessage("Could not upload the file '%s'!", file.getOriginalFilename());
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(payload);
//        }

        payload.withMessage("Unsupported file type!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        BeanUtils.assertNonNull(fileType, "Download 'fileType' must provide!");
        ResponseEntity responseEntity = null;
        InputStreamResource inputStreamResource = null;
        String contentDisposition;
        MediaType mediaType;
//        UserParser taskParser = new UserParser();
//        if (CsvParser.isCSVFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(UserParser.CSV_DOWNLOAD_FILE_NAME);
//            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
//            inputStreamResource = taskParser.buildCSVResourceStream(userService.getAll());
//        } else if (ExcelParser.isExcelFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(UserParser.EXCEL_DOWNLOAD_FILE_NAME);
//            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
//            inputStreamResource = taskParser.buildStreamResources(userService.getAll());
//        } else {
//            throw new UnsupportedOperationException("Unsupported fileType:" + fileType);
//        }
//
//        // check inputStreamResource is not null
//        if (Objects.nonNull(inputStreamResource)) {
//            responseEntity = Parser.buildOKResponse(contentDisposition, mediaType, inputStreamResource);
//        }

        return responseEntity;
    }

}

