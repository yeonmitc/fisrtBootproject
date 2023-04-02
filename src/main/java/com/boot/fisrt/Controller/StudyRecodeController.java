package com.boot.fisrt.Controller;

import com.boot.fisrt.Form.StudyForm;
import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.StudyRecord;
import com.boot.fisrt.service.MemberService;
import com.boot.fisrt.service.StudyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/study")
public class StudyRecodeController {
    @Autowired
    StudyRecordService recordService;
    @Autowired
    MemberService memberService;
    @GetMapping
    public String createForm(Model model) {

        List<Member> members = null;
        try {
            members = memberService.findAllMembers();
        }catch (Exception e){
            return "members/joinForm";
        }

        model.addAttribute("form" ,StudyForm.builder().build());
        model.addAttribute("curdate" , LocalDate.now());
        model.addAttribute("curtime" , LocalTime.now());
        model.addAttribute("members" , members);
        return "study/addForm";
    }

    @PostMapping
    public String addRecord(StudyForm form,  BindingResult rs, Long memberId) {
        if(rs.hasErrors()){
            return "study/addForm";
        }
        Member member = memberService.findMember(memberId);
        log.info("test memberid = {}" , memberId);
        if(member!=null){
        recordService.saveRecord(form,member);
        }
        return "redirect:/study/records";
    }
    @GetMapping("/records")
    public String getStudyList(Model model){
        List<Member> members = null;
        try {
            members = memberService.findAllMembers();
        }catch (Exception e){
            return "members/joinForm";
        }

        List<StudyRecord> list = recordService.getAllRecordList();
        if(list == null){
            return "study/addForm";
        }
        model.addAttribute("list" , list);


        return "/study/list";
    }

    @GetMapping("{keyId}")
    public String goUpdateRecode(@PathVariable Long keyId, Model model){
        log.info("update keyId={}",keyId);
        model.addAttribute("curdate" , LocalDate.now());
        StudyRecord record = recordService.getOneRecord(keyId);
        model.addAttribute("record" , record);


        return "/study/updateForm";
    }
    @PostMapping("/update")
    public String updateRecode(StudyForm form, Long id){
        StudyRecord record = recordService.getOneRecord(id);
        recordService.updateRecord(record,form);

        return "redirect:/study/records";
    }
    @ResponseBody
    @DeleteMapping ("{keyId}")
    public String deleteRecode(@PathVariable Long keyId){
        log.info("delete keyId={}",keyId);
        recordService.deleteRecord(keyId);
        return "ok";
    }

}
