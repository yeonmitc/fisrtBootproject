package com.boot.fisrt.service;

import com.boot.fisrt.Form.StudyForm;
import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.StudyRecord;
import com.boot.fisrt.repository.StudyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyRecordService {
    @Autowired
    StudyRecordRepository studyRepository;
    public List<StudyRecord> getAllRecordList(){
        return studyRepository.selectAll();
    }

    public StudyRecord getOneRecord(Long id){
        Optional<StudyRecord> list = studyRepository.findById(id);
        if(list == null) {
            throw new IllegalStateException("기록 데이터가 없습니다");
        }
        return list.get();
    }
    public void saveRecord(StudyForm form, Member member){
        StudyRecord record = StudyRecord.createRecord(form , member);
        studyRepository.save(record);
    }
    public void updateRecord(StudyRecord record, StudyForm form){
        StudyRecord updateRecord = StudyRecord.modyfiyRecord(record,form );
        studyRepository.save(updateRecord);
    }
    public void deleteRecord(Long id){
        studyRepository.deleteById(id);
    }
}
