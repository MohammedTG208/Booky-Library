package com.example.flightprices.booky.Repository;

import com.example.flightprices.booky.Model.ReadingList;
import com.example.flightprices.booky.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {
    ReadingList findReadingListByName(String name);

    List<ReadingList> findReadingListByUser(User user);

    ReadingList findReadingListById(Integer id);
}
