package com.appdev.lbs_springboot.controller;

import com.appdev.lbs_springboot.entity.BorrowEntity;
import com.appdev.lbs_springboot.service.BorrowService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController 
{
    @Autowired
    BorrowService bserv;

    // POST or CREATE
    @PostMapping("/postborrowrecord/student{studentid}/staff{staffid}/laptop{laptopid}")
    public BorrowEntity postBorrowRecord(@PathVariable int studentid, @PathVariable int staffid, @PathVariable int laptopid, @RequestBody BorrowEntity borrow)
    {
        return bserv.postBorrowRecord(studentid, staffid, laptopid, borrow);
    }

    // GET or READ
    @GetMapping("/getborrowrecords")
    public List<BorrowEntity> getBorrowRecords()
    {
        return bserv.getBorrowRecords();
    }

    @GetMapping("/getborrowrecord/{id}")
    public Optional<BorrowEntity> getBorrowRecordById(@PathVariable int id)
    {
        return bserv.getBorrowRecordById(id);
    }

    // PUT or UPDATE
    @PutMapping("/putborrowrecord/{id}")
    public BorrowEntity putBorrowRecord(@PathVariable int id, @RequestBody BorrowEntity updatedBorrow)
    {
        return bserv.putBorrowRecord(id, updatedBorrow);
    }

    // DELETE
    @DeleteMapping("/deleteborrowrecord/{id}")
    public String deleteBorrowRecord(@PathVariable int id)
    {
        return bserv.deleteBorrowRecord(id);
    }
}
