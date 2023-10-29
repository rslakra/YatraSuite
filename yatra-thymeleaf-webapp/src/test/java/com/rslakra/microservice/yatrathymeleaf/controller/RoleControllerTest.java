package com.rslakra.microservice.yatrathymeleaf.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.frameworks.core.enums.EntityStatus;
import com.rslakra.microservice.yatrathymeleaf.dto.account.Role;
import com.rslakra.microservice.yatrathymeleaf.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    private static final Long ROLE_ID = Long.valueOf(0);
    @Mock
    private RoleService roleService;
    @InjectMocks
    private RoleController controller;
    private Model model;
    private Role role;

    /**
     *
     */
    @BeforeEach
    void setup() {
        model = (Model) new ModelMap();
        role = new Role();
        role.setName("RoleName");
        role.setStatus(EntityStatus.INACTIVE);
    }

    @Test
    void shouldShowAllRoles() {
        List<Role> roles = new ArrayList<>();
        when(roleService.getAll()).thenReturn(roles);
        String view = controller.getAll(model);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLES);
        assertThat(model.containsAttribute(RoleController.MODEL_ATTR_ROLES));
    }

    private void mockFindRole() {
        when(roleService.getById(Long.valueOf(0))).thenReturn(role);
    }

    @Test
    void shouldShowRoleFormPage() {
        mockFindRole();
        String view = controller.editObject(model, ROLE_ID);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLE_FORM);
        assertThat(model.containsAttribute(RoleController.MODEL_ATTR_ROLES));
//        assertThat(model).containsEntry(RoleController.MODEL_ATTR_ROLE, role);
    }

    @Test
    void shouldShowRoleFormFragment() {
        mockFindRole();
        String view = controller.editObject(model, ROLE_ID);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLE_FORM + RoleController.FRAGMENT_FORM);
        assertThat(model.containsAttribute(RoleController.MODEL_ATTR_ROLES));
//        assertThat(model).containsEntry(RoleController.MODEL_ATTR_ROLE, role);
    }

    @Test
    void shouldEditRole() {
        String view = controller.editObject(model, ROLE_ID);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLE_FORM + RoleController.FRAGMENT_FORM);
        assertThat(model.containsAttribute(RoleController.MODEL_ATTR_ROLES));
        verify(roleService).update(role);
    }

    @Test
    void shouldEditRoleName() {
        mockFindRole();
        String roleName = "Test Role";
        controller.saveObjectPartially(RoleController.ID, "name", roleName);
        assertThat(role.getName()).isEqualTo(roleName);

        verify(roleService).update(role);
    }

    @Test
    void shouldEditRoleStatus() {
        mockFindRole();
        String roleStaus = EntityStatus.INACTIVE.name();
        controller.saveObjectPartially(RoleController.ID, "status", roleStaus);
        assertThat(role.getStatus().name()).isEqualTo(roleStaus);

        verify(roleService).update(role);
    }

    @Test
    void shouldNotEditRoleIfParamIsUnknown() {
        mockFindRole();
        controller.saveObjectPartially(RoleController.ID, "unsupported", "UNKNOWN");
        verify(roleService, never()).update(role);
    }

    @Test
    void shouldShowDeleteRolePage() {
        mockFindRole();
        String view = controller.showDeletePage(model, RoleController.ID);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLE_DELETE);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLE_FORM);
        assertThat(model.containsAttribute(RoleController.MODEL_ATTR_ROLES));
//        assertThat(model).containsEntry(RoleController.MODEL_ATTR_ROLE, role);
    }

    @Test
    void shouldShowDeleteRoleFormFragment() {
        mockFindRole();
        String view = controller.showDeleteFormPage(model, RoleController.ID);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLE_DELETE + RoleController.FRAGMENT_FORM);
        assertThat(model.containsAttribute(RoleController.MODEL_ATTR_ROLES));
//        assertThat(model).containsEntry(RoleController.MODEL_ATTR_ROLE, role);
    }

    @Test
    void shouldDeleteRole() {
        String view = controller.delete(model, ROLE_ID);
        assertThat(view).isEqualTo(RoleController.VIEW_ROLE_DELETE);
//        assertThat(view.isRedirectView()).isTrue();
//        assertThat(view.getUrl()).isEqualTo("/ui/roles");
        verify(roleService).delete(ROLE_ID);
    }

//    @Test
//    void shouldSetSectionToModelMap() {
//        String section = controller.showSectionPage();
//        assertThat(section).isEqualTo(RoleController.SECTION_ROLES);
//    }

}