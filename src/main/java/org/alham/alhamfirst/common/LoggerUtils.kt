package org.alham.alhamfirst.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

// 🎯 모든 클래스에서 사용할 수 있는 공통 확장 함수
//inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)
inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)