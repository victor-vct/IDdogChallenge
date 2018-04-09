package com.vctapps.iddogchallenge.login.domain

import com.vctapps.iddogchallenge.login.data.TheGuardianRepository
import com.vctapps.iddogchallenge.login.data.throwables.InvalidUser
import io.reactivex.Completable
import io.reactivex.Maybe
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

class TheGuardianTest {

    val theGuardianRepository = mock(TheGuardianRepository::class.java)

    lateinit var theGuardian: TheGuardian

    val VALID_USER = "test@mail.com"

    val VALID_TOKEN = "123"

    val INVALID_USER = "123"

    @Before
    fun setUp(){
        theGuardian = TheGuardianImpl(theGuardianRepository)
    }

    @Test
    fun `check if can come in without user stored`(){
        given(theGuardianRepository.hasUserStored())
                .willReturn(Maybe.just(true))

        theGuardian.checkCanComeIn()
                .test()
                .assertValue(true)

    }

    @Test
    fun `check if can come in with user stored`(){
        given(theGuardianRepository.hasUserStored())
                .willReturn(Maybe.just(false))

        theGuardian.checkCanComeIn()
                .test()
                .assertValue(false)

    }

    @Test
    fun `check get token without user stored`(){
        given(theGuardianRepository.getToken())
                .willReturn(Maybe.empty())

        val observer = theGuardian.getToken()
                .test()
                .assertComplete()

        assertThat(observer.values().size, equalTo(0))

    }

    @Test
    fun `check get token with user stored`(){
        given(theGuardianRepository.getToken())
                .willReturn(Maybe.just(VALID_TOKEN))

        val observer = theGuardian.getToken()
                .test()
                .assertComplete()

        assertThat(observer.values()[0], equalTo(VALID_TOKEN))

    }

    @Test
    fun `check authenticate user`(){
        given(theGuardianRepository.saveUser(VALID_USER))
                .willReturn(Completable.complete())

        theGuardian.doLogin(VALID_USER)
                .test()
                .assertComplete()
    }

    @Test
    fun `check cant authenticate user`(){
        given(theGuardianRepository.saveUser(INVALID_USER))
                .willReturn(Completable.error(InvalidUser()))

        theGuardian.doLogin(INVALID_USER)
                .test()
                .assertError(InvalidUser::class.java)
    }

}