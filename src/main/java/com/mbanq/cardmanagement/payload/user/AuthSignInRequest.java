package com.mbanq.cardmanagement.payload.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel(value ="AuthSignInRequest")
public class AuthSignInRequest {

  @NotNull(message = "Name Not Null")
  @ApiModelProperty(value = "username  of auth", required = true, example = "phally")
  private String username;

  @NotNull(message = "password Not Null")
  @ApiModelProperty(value = "password  of auth", required = true, example = "123456")
  private String password;


}
