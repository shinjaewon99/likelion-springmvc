package com.example.likelion;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LottoService {

    private int hits = 0;

    public int addHit() {
        hits++;
        return hits;
    }


    public List<Integer> lotto(){
        List<Integer> lottoList = new ArrayList<>();
        Random random = new Random();
        while(lottoList.size() < 6){
            int randomNumber = random.nextInt(45) + 1; // bound 값은 포함하지 않음으로 0 ~ 44가 된다. 그래서 + 1
            if(!lottoList.contains(randomNumber)){
                lottoList.add(randomNumber);
            }
        }
        return lottoList;
    }

}
