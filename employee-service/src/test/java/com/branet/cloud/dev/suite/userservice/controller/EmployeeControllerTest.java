package com.branet.cloud.dev.suite.userservice.controller;

import static org.mockito.Mockito.*;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private EmployeeService employeeService;

  @Test
  public void testGetAll() throws Exception {
    List<Employee> mockEmployees = Arrays.asList(new Employee(), new Employee());
    when(employeeService.getAll()).thenReturn(mockEmployees);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/employee"))
        .andExpect(MockMvcResultMatchers.status().isForbidden())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockEmployees.size()));

    verify(employeeService, times(1)).getAll();
  }

  @Test
  public void testGetAllByProjectId() throws Exception {
    Long projectId = 1L;
    List<Employee> mockEmployees = Arrays.asList(new Employee(), new Employee());
    when(employeeService.getAllByProjectId(projectId)).thenReturn(mockEmployees);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/employee/getAllByProject/{projectId}", projectId))
        //                .andExpect(MockMvcResultMatchers.status().isForbidden())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockEmployees.size()));

    verify(employeeService, times(1)).getAllByProjectId(projectId);
  }

  @Test
  public void testCreateEmployee() throws Exception {
    CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
    Employee mockEmployee = new Employee();
    when(employeeService.createEmployee(createEmployeeRequest)).thenReturn(mockEmployee);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    verify(employeeService, times(1)).createEmployee(createEmployeeRequest);
  }

  @Test
  @WithMockUser(authorities = {"TEAM_LEAD"})
  public void testGetByPositionAndOrExperience() throws Exception {
    List<Employee> mockEmployees = Arrays.asList(new Employee(), new Employee());
    when(employeeService.getByPositionAndExperience("Developer", "Intermediate"))
        .thenReturn(mockEmployees);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/employee/getByPositionAndOrExperience")
                .param("position", "Developer")
                .param("experience", "Intermediate"))
        .andExpect(MockMvcResultMatchers.status().isForbidden())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockEmployees.size()));

    verify(employeeService, times(1)).getByPositionAndExperience("Developer", "Intermediate");
  }

  @Test
  public void testGetEmployeeById() throws Exception {
    Long employeeId = 1L;
    Employee mockEmployee = new Employee();
    when(employeeService.getEmployee(employeeId)).thenReturn(mockEmployee);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/employee/{employeeId}", employeeId))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    verify(employeeService, times(1)).getEmployee(employeeId);
  }

  @Test
  public void testAddProjectToEmployee() throws Exception {
    Long employeeId = 1L;
    Long projectId = 2L;

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/employee/{employeeId}/addProject", employeeId)
                .param("projectId", String.valueOf(projectId)))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    verify(employeeService, times(1)).addProjectToEmployee(employeeId, projectId);
  }

  @Test
  public void testRemoveProjectFromEmployee() throws Exception {
    Long employeeId = 1L;
    Long projectId = 2L;

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/employee/{employeeId}/removeProject", employeeId)
                .param("projectId", String.valueOf(projectId)))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    verify(employeeService, times(1)).removeProjectFromEmployee(employeeId, projectId);
  }

  @Test
  public void testGetByEmail() throws Exception {
    String email = "john.doe@example.com";
    Employee mockEmployee = new Employee();
    when(employeeService.getEmployee(email)).thenReturn(mockEmployee);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/employee/getByEmail").param("email", email))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    verify(employeeService, times(1)).getEmployee(email);
  }

  @Test
  public void testUpdateEmployee() throws Exception {
    Long employeeId = 1L;
    CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
    Employee mockEmployee = new Employee();
    when(employeeService.updateEmployee(createEmployeeRequest, employeeId))
        .thenReturn(mockEmployee);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/employee")
                .param("employeeId", String.valueOf(employeeId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    verify(employeeService, times(1)).updateEmployee(createEmployeeRequest, employeeId);
  }

  @Test
  public void testDeleteEmployee() throws Exception {
    Long employeeId = 1L;

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/employee/{employeeId}", employeeId))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    verify(employeeService, times(1)).deleteEmployee(employeeId);
  }
}
