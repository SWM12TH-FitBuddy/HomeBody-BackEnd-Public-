package tech.homebody.homebodyuser.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7365630693393356988L;

    @Schema(example = "uid", description = "유저 id")
    private String uid;

    @Schema(example = "name", description = "유저 이름")
    private String name;

    @Schema(example = "email@email.email", description = "유저 email")
    private String email;

    @Schema(example = "true", description = "이메일 인증 여부")
    private boolean isEmailVerified;

    @Schema(example = "token maker url", description = "토큰 발급자 url")
    private String issuer;

    @Schema(example = "picture url", description = "유저 프로필 위치 정보")
    private String picture;

}