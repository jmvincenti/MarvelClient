package com.jmvincenti.marvelcharacters.data.api.characters

import com.jmvincenti.marvelcharacters.data.api.BaseApiClient

/**
 * Client for Character model calls
 */
class CharactersClient : BaseApiClient() {

    private val service: CharactersService by lazy { getApi(CharactersService::class.java) }

    fun getCharacters(offset: Int = 0, limit: Int, startName: String? = null) = service.getCharacters(offset, limit, startName)

    fun getCharacter(id: Int) = service.getCharacter(id)

}
