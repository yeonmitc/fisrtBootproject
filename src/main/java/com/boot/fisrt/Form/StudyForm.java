package com.boot.fisrt.Form;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@Builder
public class StudyForm {
    @NotNull(message = "회원 선택해주세요")
    private Long memberId;
    @PastOrPresent(message = "현재날짜 이상값 입력불가")
    private String studyDay;
    @PastOrPresent(message = "현재시간 이상값 입력불가")
    private String startTime;
    @Min(value = 1 , message = "최소 1분이상 기록해야합니다")
    @Max(value = 300, message = "최대 300분까지 기록가능")
    private int studyMins;
    @NotEmpty(message = "공부 내용은 필수 값입니다")
    private String contents;
}
