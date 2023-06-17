package org.tvz.logmetrix.rest.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.tvz.logmetrix.util.TestContextConfig;

@ContextConfiguration(classes = TestContextConfig.class)
@AutoConfigureMockMvc
@WebMvcTest
@WithMockUser
public class BaseControllerTest {
}