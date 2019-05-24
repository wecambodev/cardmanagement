/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mbanq.cardmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.RequestDispatcher;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestAuthTestsCase {

	private static final String URL_SIGN_UP = "/api/v1/auth/signup";
	private static final String URL_SIGN_IN = "/api/v1/auth/signin";

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");


	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.build();
	}


	@Test
	public void signUp() throws Exception {

		Map<String, Object> user = new HashMap<String, Object>();
		user.put("name", "phuong phally");
		user.put("username", "phuongphally");
		user.put("password", "phuongphally");
		user.put("email", "phuongphally@gmail.com");
		user.put("role", "admin");
		this.mockMvc.perform(
				post(URL_SIGN_UP).contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(user))).andExpect(
				status().isCreated());

	}


	@Test
	public void signIn() throws Exception {

		Map<String, Object> user = new HashMap<String, Object>();
		user.put("name", "phuong phally");
		user.put("username", "test1");
		user.put("password", "test1");
		user.put("email", "test1@gmail.com");
		user.put("role", "admin");
		this.mockMvc.perform(
				post(URL_SIGN_UP).contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(user))).andExpect(
				status().isCreated());


		this.mockMvc.perform(post(URL_SIGN_IN).contentType(MediaTypes.HAL_JSON)
				.content(this.objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());
	}
}
