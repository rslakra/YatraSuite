package com.rslakra.microservice.yatrathymeleaf.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.core.Payload;
import com.rslakra.frameworks.spring.controller.web.AbstractWebController;
import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import com.rslakra.frameworks.spring.filter.Filter;
import com.rslakra.frameworks.spring.parser.Parser;
import com.rslakra.microservice.common.exception.InvalidUUIDException;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.yatrathymeleaf.dto.ride.Ride;
import com.rslakra.microservice.yatrathymeleaf.framework.AuthUtils;
import com.rslakra.microservice.yatrathymeleaf.service.RideService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST Controller to manage ride activities
 */

@Controller
@RequestMapping("${uiPrefix}/rides")
public class RideController extends AbstractWebController<Ride, Long> {

    private final Logger LOGGER = LogManager.getLogger(RideController.class);

    public static final String VIEW_RIDES = "views/ride/listRides";
    public static final String REDIRECT_VIEW_RIDES = "redirect:/ui/rides";
    public static final String VIEW_RIDE_FORM = "views/ride/editRide";
    public static final String VIEW_RIDE_DELETE = "views/ride/deleteRide";
    public static final String VIEW_UPLOAD_RIDES = "views/ride/uploadRide";
    public static final String MODEL_ATTR_RIDES = "rides";
    public static final String MODEL_ATTR_RIDE = "ride";
    public static final String FRAGMENT_FORM = " :: form";
    public static final String SECTION_RIDES = "rides";
    public static final String ID = "id";
    public static final String PATH_ID = "/{id}";
    public static final String X_REQUESTED_WITH_XML_HTTP_REQUEST = "X-Requested-With=XMLHttpRequest";

    private final RideService rideService;

    /**
     * @param rideService
     */
    @Autowired
    public RideController(RideService rideService) {
        LOGGER.debug("RideController({})", rideService);
        this.rideService = rideService;
    }

    /**
     * @return
     */
    @Override
    public Parser<Ride> getParser() {
        return null;
    }

    /**
     * Saves the <code>T</code> object.
     *
     * @param ride
     * @return
     */
    @PostMapping({"/", "/save"})
    @Override
    public String save(@RequestBody @Validated @ModelAttribute("ride") Ride ride) {
        if (BeanUtils.isNotNull(ride.getId())) {
            Ride oldRide = rideService.getById(ride.getId());
            BeanUtils.copyProperties(ride, oldRide);
            ride = rideService.update(oldRide);
        } else {
            ride = rideService.create(ride);
        }

        return REDIRECT_VIEW_RIDES;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @param model
     * @return
     */
    @Override
    public String getAll(Model model) {
        LOGGER.debug("+getAll()");
        LOGGER.info("[GET] /ui/rides");
        List<Ride> rides = rideService.getAll();
        model.addAttribute(MODEL_ATTR_RIDES, rides);
        LOGGER.debug("-getAll(), rides:{}", rides);
        return VIEW_RIDES;
    }

    /**
     * Returns the list of user's rides.
     *
     * @return
     */
    @GetMapping()
    public String getUserRides(Model model) {
        LOGGER.debug("+getUserRides()");
        String email = AuthUtils.getUserEmail();
        LOGGER.info("[GET] ui/rides?email={}", email);
        List<Ride> rides = rideService.getRidesByEmail(email);
        model.addAttribute(MODEL_ATTR_RIDES, rides);
        LOGGER.debug("-getAll(), listRides={} rides:{}", VIEW_RIDES, rides);
        return VIEW_RIDES;
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
        Ride ride = null;
        if (BeanUtils.isNotNull(id)) {
            ride = rideService.getById(id);
            if (BeanUtils.isNull(ride)) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        } else {
            ride = new Ride();
        }
        model.addAttribute(MODEL_ATTR_RIDE, ride);
        LOGGER.debug("-editObject(), editObject:{}, ride:{}", VIEW_RIDE_FORM, ride);
        return VIEW_RIDE_FORM;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID, headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String editObjectPartially(Model model, @PathVariable(ID) Long id) {
        LOGGER.info("Requesting ride={} via XHR request.", id);
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
        Ride ride = rideService.getById(BeanUtils.asType(id, Long.class));
        if (BeanUtils.isNull(ride)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        // validate the param key/value and edit it.
        if ("email".equalsIgnoreCase(paramName)) {
            ride.setUserEmail(paramValue);
        } else if ("vehicleId".equalsIgnoreCase(paramName)) {
            ride.setVehicleId(UUID.fromString(paramValue));
        } else {
            LOGGER.warn("Invalid Update Ride Request! Parameter name=[{}], value=[{}]", paramName, paramValue);
            return;
        }

        ride = rideService.update(ride);
        LOGGER.debug("-saveObjectPartially(), ride:{}", ride);
    }

    /**
     * Displays Delete Ride Page.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping({"/delete/{id}", PATH_ID + "/delete"})
    public String showDeletePage(Model model, @PathVariable(ID) String id) {
        LOGGER.debug("+showDeletePage({}, {})", model, id);
        Ride ride = rideService.getById(BeanUtils.asType(id, Long.class));
        if (BeanUtils.isNull(ride)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute(MODEL_ATTR_RIDE, ride);
        LOGGER.debug("-showDeletePage(), deleteRidePage:{}", VIEW_RIDE_DELETE);
        return VIEW_RIDE_DELETE;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = PATH_ID + "/delete", headers = {X_REQUESTED_WITH_XML_HTTP_REQUEST})
    public String showDeleteFormPage(Model model, @PathVariable(value = ID) String id) {
        LOGGER.info("Requesting delete ride={} via XHR request.", id);
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

        rideService.delete(id);
        LOGGER.debug("-delete(), redirectView:{}", REDIRECT_VIEW_RIDES);
        return REDIRECT_VIEW_RIDES;
    }

    /**
     * Starts a ride on this vehicle for this user.
     *
     * @param startRideRequest a POJO holding the json that was passed in
     * @return Json with details about the started ride
     * @throws NotFoundException            if the vehicle or user is not found
     * @throws InvalidUUIDException         if the passed vehicleId string is not a valid UUID
     * @throws InvalidVehicleStateException if the requested vehicle is not already marked "in use"
     */
    @PostMapping("/start")
    public String startRide(Model model, @RequestBody Map<String, Object> startRideRequest)
        throws NotFoundException, InvalidUUIDException, InvalidVehicleStateException {
        LOGGER.debug("+startRide({}, {})", model, startRideRequest);
        LOGGER.info("[POST] /ui/rides/start");
        Payload payload = Payload.newBuilder();
        payload.putAll(startRideRequest);
        Ride ride = rideService.startRide(payload);
        model.addAttribute(MODEL_ATTR_RIDE, ride);
        LOGGER.debug("-startRide(), viewRide:{}, ride: {}", VIEW_RIDE_FORM, ride);
        return VIEW_RIDE_FORM;
    }

    /**
     * Ends this specific ride (also calculates time, distance, and speed travelled).
     *
     * @param endRideRequest a POJO holding the json that was passed in
     * @return a message about the time, speed and distance travelled
     */
    @PostMapping("/end")
    public String endRide(Model model, @RequestBody @Validated Map<String, Object> endRideRequest) {
        LOGGER.debug("+endRide({}, {})", model, endRideRequest);
        LOGGER.info("[POST] /ui/rides/end");
        Payload payload = Payload.newBuilder();
        payload.putAll(endRideRequest);
        Ride ride = rideService.endRide(payload);
        model.addAttribute(MODEL_ATTR_RIDE, ride);
        LOGGER.debug("-endRide(), viewRide:{}, ride: {}", VIEW_RIDE_FORM, ride);
        return VIEW_RIDE_FORM;
    }

}

