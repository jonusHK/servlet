package com.bhkpo.servlet.domain.member

import java.util.HashMap

/**
 * 동시성 문제가 고려되어 있지 않음, 현업에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
class MemberRepository private constructor() {

    companion object {
        @JvmStatic
        private var store = HashMap<Long, Member>()
        @JvmStatic
        private var sequence = 0L
        @JvmStatic
        private val instance = MemberRepository()
        @JvmStatic
        fun getInstance(): MemberRepository {
            return instance
        }
    }

    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id] = member
        return member
    }

    fun findById(id: Long): Member? {
        return store[id]
    }

    fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }
}
