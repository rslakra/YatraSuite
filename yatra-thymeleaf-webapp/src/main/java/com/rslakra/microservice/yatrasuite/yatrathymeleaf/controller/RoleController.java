package com.rslakra.microservice.yatrasuite.yatrathymeleaf.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.appsuite.core.Payload;
import com.rslakra.appsuite.core.enums.EntityStatus;
import com.rslakra.appsuite.spring.controller.web.AbstractWebController;
import com.rslakra.appsuite.spring.exception.InvalidRequestException;
import com.rslakra.appsuite.spring.filter.Filter;
import com.rslakra.appsuite.spring.parser.Parser;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.account.Role;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.service.RoleService;
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
@RequestMapping("${uiPrefix}/roles")
public class RoleController extends AbstractWebController<Role, Long> {

    private final Logger LOGGER = LogManager.getLogger(RoleController.class);

    public static final String VIEW_ROLES = "views/role/listRoles";
    public static final String REDIRECT_VIEW_ROLES = "redirect:/ui/roles";
    public static final String VIEW_ROLE_FORM = "views/role/editRole";
    public static final String VIEW_ROLE_DELETE = "views/role/deleteRole";
    public static final String VIEW_UPLOAD_ROLES = "views/role/uploadRole";
    public static final String MODEL_ATTR_ROLES = "roles";
    public static final String MODEL_ATTR_ROLE = "role";
    public static final String FRAGMENT_FORM = " :: form";
    public static final String SECTION_ROLES = "roles";
    public static final String ID = "id";
    public static final String PATH_ID = "/{id}";
    public static final String X_REQUESTED_WITH_XML_HTTP_REQUEST = "X-Requested-With=XMLHttpRequest";

    private final RoleService roleService;

    /**
     * @param roleService
     */
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Saves the <code>T</code> object.
     *
     * @param role
     * @return
     */
    @PostMapping({"/", "/save"})
    @Override
    public String save(@RequestBody @Validated @ModelAttribute("role") Role role) {
        if (BeanUtils.isNotNull(role.getId())) {
            Role oldRole = roleService.getById(role.getId());
            BeanUtils.copyProperties(role, oldRole);
            role = roleService.update(oldRole);
        } else {
            role = roleService.create(role);
        }

        return REDIRECT_VIEW_ROLES;
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
        LOGGER.info("[GET] /ui/roles");
        List<Role> roles = roleService.getAll();
        model.addAttribute(MODEL_ATTR_ROLES, roles);
        LOGGER.debug("-getAll(), listRoles:{}", VIEW_ROLES);
        return VIEW_ROLES;
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
        Role role = null;
        if (BeanUtils.isNotNull(id)) {
            role = roleService.getById(id);
            if (BeanUtils.isNull(role)) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        } else {
            role = new Role();
        }
        model.addAttribute(MODEL_ATTR_ROLE, role);
        LOGGER.debug("-editObject(), editObject:{}, role:{}", VIEW_ROLE_FORM, role);
        return VIEW_ROLE_FORM;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID, headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String editObjectPartially(Model model, @PathVariable(ID) Long id) {
        LOGGER.info("Requesting role={} via XHR request.", id);
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
    public void saveObjectPartially(@RequestParam("pk") String id, @RequestParam("name") String paramName,
                                    @RequestParam("value") String paramValue) {
        LOGGER.debug("+saveObjectPartially({}, {}, {})", id, paramName, paramValue);
        Role role = roleService.getById(BeanUtils.asType(id, Long.class));
        if (BeanUtils.isNull(role)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        // validate the param key/value and edit it.
        if ("name".equalsIgnoreCase(paramName)) {
            role.setName(paramValue);
        } else if ("status".equalsIgnoreCase(paramName)) {
            role.setStatus(EntityStatus.ofString(paramValue));
        } else {
            LOGGER.warn("Invalid Update Role Request! Parameter name=[{}], value=[{}]", paramName, paramValue);
            return;
        }

        role = roleService.update(role);
        LOGGER.debug("-saveObjectPartially(), role:{}", role);
    }

    /**
     * Displays Delete Role Page.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping({"/delete/{id}", PATH_ID + "/delete"})
    public String showDeletePage(Model model, @PathVariable(ID) String id) {
        LOGGER.debug("+showDeletePage({}, {})", model, id);
        Role role = roleService.getById(BeanUtils.asType(id, Long.class));
        if (BeanUtils.isNull(role)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute(MODEL_ATTR_ROLE, role);
        LOGGER.debug("-showDeletePage(), deleteRolePage:{}", VIEW_ROLE_DELETE);
        return VIEW_ROLE_DELETE;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID + "/delete", headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String showDeleteFormPage(Model model, @PathVariable(value = ID) String id) {
        LOGGER.info("Requesting delete role={} via XHR request.", id);
        // Let Thymeleaf only return the th:fragment="form" within the view
        return showDeletePage(model, id) + FRAGMENT_FORM;
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

        roleService.delete(id);
        LOGGER.debug("-delete(), redirectView:{}", REDIRECT_VIEW_ROLES);
        return REDIRECT_VIEW_ROLES;
    }

    /**
     * @return
     */
    @Override
    public Parser<Role> getParser() {
        return null;
    }

    /**
     * Displays the upload <code>Roles</code> UI.
     *
     * @return
     */
    @GetMapping(path = {"/upload"})
    public String showUploadPage() {
        return VIEW_UPLOAD_ROLES;
    }

    /**
     * Uploads the file of <code>Roles</code>.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
//        try {
//            List<Role> roles = null;
//            RoleParser roleParser = new RoleParser();
//            if (CsvParser.isCSVFile(file)) {
//                roles = roleParser.readCSVStream(file.getInputStream());
//            } else if (ExcelParser.isExcelFile(file)) {
//                roles = roleParser.readStream(file.getInputStream());
//            }
//
//            // check the task list is available
//            if (Objects.nonNull(roles)) {
//                roles = roleService.create(roles);
//                LOGGER.debug("roles: {}", roles);
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
//        RoleParser taskParser = new RoleParser();
//        if (CsvParser.isCSVFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(RoleParser.CSV_DOWNLOAD_FILE_NAME);
//            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
//            inputStreamResource = taskParser.buildCSVResourceStream(roleService.getAll());
//        } else if (ExcelParser.isExcelFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(RoleParser.EXCEL_DOWNLOAD_FILE_NAME);
//            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
//            inputStreamResource = taskParser.buildStreamResources(roleService.getAll());
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

    /**
     * Displays role's section.
     *
     * @return
     */
//    @ModelAttribute("section")
//    public String showSectionPage() {
//        return SECTION_ROLES;
//    }

}

