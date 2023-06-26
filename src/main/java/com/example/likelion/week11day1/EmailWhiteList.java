package com.example.likelion.week11day1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 어노테이션을 어디에 적용할지 선택

public @interface EmailWhiteList {
}
