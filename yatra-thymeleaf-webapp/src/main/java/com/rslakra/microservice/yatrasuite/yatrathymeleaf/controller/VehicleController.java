package com.rslakra.microservice.yatrasuite.yatrathymeleaf.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.devamatre.appsuite.core.BeanUtils;
import com.devamatre.appsuite.core.Payload;
import com.devamatre.appsuite.spring.controller.web.AbstractWebController;
import com.devamatre.appsuite.spring.exception.InvalidRequestException;
import com.devamatre.appsuite.spring.filter.Filter;
import com.devamatre.appsuite.spring.parser.Parser;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.vehicle.Vehicle;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.service.VehicleService;
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
import java.util.UUID;

/**
 * REST Controller to manage basic vehicle activities
 */

@Controller
@RequestMapping("${uiPrefix}/vehicles")
@Validated
public class VehicleController extends AbstractWebController<Vehicle, UUID> {

    private final Logger LOGGER = LogManager.getLogger(VehicleController.class);

    public static final String VIEW_VEHICLES = "views/vehicle/listVehicles";
    public static final String REDIRECT_VIEW_VEHICLES = "redirect:/ui/vehicles";
    public static final String VIEW_VEHICLE_FORM = "views/vehicle/editVehicle";
    public static final String VIEW_VEHICLE_DELETE = "views/vehicle/deleteVehicle";
    public static final String VIEW_UPLOAD_VEHICLES = "views/vehicle/uploadVehicle";
    public static final String MODEL_ATTR_VEHICLES = "vehicles";
    public static final String MODEL_ATTR_VEHICLE = "vehicle";
    public static final String FRAGMENT_FORM = " :: form";
    public static final String SECTION_VEHICLES = "vehicles";
    public static final String ID = "id";
    public static final String PATH_ID = "/{id}";
    public static final String X_REQUESTED_WITH_XML_HTTP_REQUEST = "X-Requested-With=XMLHttpRequest";

    private final VehicleService vehicleService;

    /**
     * @param vehicleService
     */
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Saves the <code>T</code> object.
     *
     * @param vehicle
     * @return
     */
    @PostMapping({"/", "/save"})
    @Override
    public String save(@RequestBody @Validated @ModelAttribute("vehicle") Vehicle vehicle) {
        if (BeanUtils.isNotNull(vehicle.getId())) {
            Vehicle oldVehicle = vehicleService.getById(vehicle.getId());
            BeanUtils.copyProperties(vehicle, oldVehicle);
            vehicle = vehicleService.update(oldVehicle);
        } else {
            vehicle = vehicleService.create(vehicle);
        }

        return REDIRECT_VIEW_VEHICLES;
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
        LOGGER.info("[GET] /ui/vehicles");
        List<Vehicle> vehicles = vehicleService.getAll();
        model.addAttribute(MODEL_ATTR_VEHICLES, vehicles);
        LOGGER.debug("-getAll(), listVehicles:{}", VIEW_VEHICLES);
        return VIEW_VEHICLES;
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
    public String filter(Model model, Map<String, Object> allParams) {
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
    public String editObject(Model model, @PathVariable(value = ID, required = false) UUID id) {
        LOGGER.debug("+editObject({}, {})", model, id);
        Vehicle vehicle = null;
        if (BeanUtils.isNotNull(id)) {
            vehicle = vehicleService.getById(id);
            if (BeanUtils.isNull(vehicle)) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        } else {
            vehicle = new Vehicle();
        }
        model.addAttribute(MODEL_ATTR_VEHICLE, vehicle);
        LOGGER.debug("-editObject(), editObject:{}, vehicle:{}", VIEW_VEHICLE_FORM, vehicle);
        return VIEW_VEHICLE_FORM;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID, headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String editObjectPartially(Model model, @PathVariable(ID) UUID id) {
        LOGGER.info("Requesting vehicle={} via XHR request.", id);
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
    public void saveObjectPartially(@RequestParam("pk") UUID id, @RequestParam("name") String paramName,
                                    @RequestParam("value") String paramValue) {
        LOGGER.debug("+saveObjectPartially({}, {}, {})", id, paramName, paramValue);
        Vehicle vehicle = vehicleService.getById(id);
        if (BeanUtils.isNull(vehicle)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

//        // validate the param key/value and edit it.
//        if ("email".equalsIgnoreCase(paramName)) {
//            vehicle.setEmail(paramValue);
//        } else if ("firstName".equalsIgnoreCase(paramName)) {
//            vehicle.setFirstName(paramValue);
//        } else if ("middleName".equalsIgnoreCase(paramName)) {
//            vehicle.setMiddleName(paramValue);
//        } else if ("lastName".equalsIgnoreCase(paramName)) {
//            vehicle.setLastName(paramValue);
//        } else if ("status".equalsIgnoreCase(paramName)) {
//            vehicle.setStatus(EntityStatus.ofString(paramValue));
//        } else {
//            LOGGER.warn("Invalid Vehicle's Update Request! Parameter name=[{}], value=[{}]", paramName, paramValue);
//            return;
//        }

        vehicle = vehicleService.update(vehicle);
        LOGGER.debug("-saveObjectPartially(), vehicle:{}", vehicle);
    }

    /**
     * Displays Delete Vehicle Page.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping({"/delete/{id}", PATH_ID + "/delete"})
    public String showDeletePage(Model model, @PathVariable(ID) UUID id) {
        LOGGER.debug("+showDeletePage({}, {})", model, id);
        Vehicle vehicle = vehicleService.getById(id);
        if (BeanUtils.isNull(vehicle)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute(MODEL_ATTR_VEHICLE, vehicle);
        LOGGER.debug("-showDeletePage(), deleteVehiclePage:{}", VIEW_VEHICLE_DELETE);
        return VIEW_VEHICLE_DELETE;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID + "/delete", headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String showDeleteFormPage(Model model, @PathVariable(ID) UUID id) {
        LOGGER.info("Requesting delete vehicle={} via XHR request.", id);
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
    public String delete(Model model, @PathVariable(ID) UUID id) {
        LOGGER.debug("+delete({}, {})", model, id);
        if (BeanUtils.isEmpty(id)) {
            throw new InvalidRequestException("The ID should provide!");
        }
        vehicleService.delete(id);
        LOGGER.debug("-delete(), redirectView:{}", REDIRECT_VIEW_VEHICLES);
        return REDIRECT_VIEW_VEHICLES;
    }

//    /**
//     * Deletes a vehicle.
//     *
//     * @param email the email of the vehicle to delete
//     * @return a message indicated the vehicle was deleted
//     */
//    @DeleteMapping("/{email}")
//    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable String email) {
//        LOGGER.debug("+delete({})", email);
//        LOGGER.info("[DELETE] /ui/vehicles/{email}");
//        Vehicle vehicle = vehicleService.getById(email);
//        vehicleService.delete(vehicle.getId());
//        LOGGER.debug("-delete(), vehicle:{}", vehicle);
//        return ResponseEntity.ok(vehicle);
//    }

    /**
     * @return
     */
    @Override
    public Parser<Vehicle> getParser() {
        return null;
    }

    /**
     * Displays the upload <code>Vehicles</code> UI.
     *
     * @return
     */
    @GetMapping(path = {"/upload"})
    public String showUploadPage() {
        return VIEW_UPLOAD_VEHICLES;
    }

    /**
     * Uploads the file of <code>Vehicles</code>.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
//        try {
//            List<Vehicle> vehicles = null;
//            VehicleParser vehicleParser = new VehicleParser();
//            if (CsvParser.isCSVFile(file)) {
//                vehicles = vehicleParser.readCSVStream(file.getInputStream());
//            } else if (ExcelParser.isExcelFile(file)) {
//                vehicles = vehicleParser.readStream(file.getInputStream());
//            }
//
//            // check the task list is available
//            if (Objects.nonNull(vehicles)) {
//                vehicles = vehicleService.create(vehicles);
//                LOGGER.debug("vehicles: {}", vehicles);
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
//        VehicleParser taskParser = new VehicleParser();
//        if (CsvParser.isCSVFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(VehicleParser.CSV_DOWNLOAD_FILE_NAME);
//            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
//            inputStreamResource = taskParser.buildCSVResourceStream(vehicleService.getAll());
//        } else if (ExcelParser.isExcelFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(VehicleParser.EXCEL_DOWNLOAD_FILE_NAME);
//            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
//            inputStreamResource = taskParser.buildStreamResources(vehicleService.getAll());
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

//
//    /**
//     * Adds a vehicle.
//     *
//     * @param newVehicleDTO a POJO holding the json that was passed in containing the vehicle details
//     * @return the generated uuid (key) of the added vehicle
//     */
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> addVehicle(@RequestBody Map<String, Object> newVehicleDTO) {
//        LOGGER.info("[POST] /ui/vehicles");
//        Map<String, Object> response = vehicleClient.addVehicle(newVehicleDTO);
//        return ResponseEntity.ok(response);
//    }
//
//    /**
//     * Gets a list of vehicles with their location(limited by passed value).
//     *
//     * @param maxVehicles the maximum number of vehicle rows to return
//     * @return a json array containing the vehicle details
//     */
//    @GetMapping
//    public ResponseEntity<List<Map<String, Object>>> getVehiclesWithLocation(
//        @RequestParam(value = "max_vehicles", required = false) @Min(1) Integer maxVehicles) {
//        LOGGER.info("[GET] /ui/vehicles");
//        List<Map<String, Object>> vehiclesWithLocations = vehicleClient.getVehiclesWithLocation(maxVehicles);
//        return ResponseEntity.ok(vehiclesWithLocations);
//    }
//
//    /**
//     * @param vehicleId
//     * @return
//     * @throws InvalidUUIDException
//     */
//    @GetMapping("/location/{vehicleId}")
//    public ResponseEntity<Map<String, Object>> getVehicleWithLocation(@PathVariable String vehicleId)
//        throws InvalidUUIDException {
//        LOGGER.info("[GET] /ui/vehicles/location/{}", vehicleId);
//        Map<String, Object> vehicleWithLocation = vehicleClient.getVehicleWithLocation(
//            CommonUtils.toUUID(vehicleId, Constants.ERR_INVALID_VEHICLE_ID));
//        return ResponseEntity.ok(vehicleWithLocation);
//    }
//
//    /**
//     * Gets a specific vehicle with its location history.
//     *
//     * @param vehicleId the uuid of the vehicle to return location history for
//     * @return json with the vehicle details and a json array of all its past locations
//     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
//     */
//    @GetMapping("/{vehicleId}")
//    public ResponseEntity<Map<String, Object>> getVehicleWithHistory(@PathVariable String vehicleId)
//        throws InvalidUUIDException {
//        LOGGER.info("[GET] /ui/vehicles/{}", vehicleId);
//        Map<String, Object>
//            vehicle =
//            vehicleClient.getVehicleWithHistory(CommonUtils.toUUID(vehicleId, Constants.ERR_INVALID_VEHICLE_ID));
//        return ResponseEntity.ok(vehicle);
//    }
//
//    /**
//     * Removes a specific vehicle.
//     *
//     * @param vehicleId the uuid of the vehicle to delete
//     * @return "nothing"
//     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
//     */
//    @DeleteMapping("/{vehicleId}")
//    public ResponseEntity<Payload> removeVehicle(@PathVariable String vehicleId)
//        throws InvalidUUIDException {
//        LOGGER.info("[DELETE] /ui/vehicles/{vehicleId}");
//        Map<String, Object>
//            messages =
//            vehicleClient.removeVehicle(CommonUtils.toUUID(vehicleId, Constants.ERR_INVALID_VEHICLE_ID));
//        return ResponseEntity.ok(messages);
//    }

}

