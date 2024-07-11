package com.example.ShareFit.common.swagger;

import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 API")
public interface MemberControllerDocs {
    @Operation(summary = "회원 조회", description = "파라미터로 받은 데이터를 활용하여 회원 조회")
    @Parameter(name = "id", description = "회원 ID")
    ResponseEntity<MemberResponseDto> find(@PathVariable("member_id") Long id);

    @Operation(summary = "회원 수정", description = "파라미터로 받은 데이터를 활용하여 회원 수정")
    @Parameter(name = "id", description = "회원 ID")
    ResponseEntity<MemberResponseDto> update(@PathVariable("member_id") Long id,
                                                    @RequestBody MemberUpdateDto memberUpdateDto);

    @Operation(summary = "회원 탈퇴", description = "파라미터로 받은 데이터를 활용하여 회원 탈퇴")
    @Parameter(name = "id", description = "회원 ID")
    ResponseEntity<Void> delete(@PathVariable("member_id") Long id);
}
