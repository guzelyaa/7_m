package com.example.m7l1.data.base

import com.example.m7l1.data.totNoteEntity
import com.example.m7l1.domain.utils.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend ()-> T)= flow{
        emit(ResultStatus.Loading())
        try {
            val data = request()
            emit(ResultStatus.Success(data))
        }catch (e: java.io.IOException){
            emit(ResultStatus.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)}