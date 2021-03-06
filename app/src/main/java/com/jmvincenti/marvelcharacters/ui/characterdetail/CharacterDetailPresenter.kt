package com.jmvincenti.marvelcharacters.ui.characterdetail

import com.jmvincenti.marvelcharacters.data.model.ApiUrl
import com.jmvincenti.marvelcharacters.data.model.Character

class CharacterDetailPresenter<V : CharacterDetailContract.View> : CharacterDetailContract.Presenter<V> {


    var mView: V? = null


    override fun setView(view: V) {
        mView = view
    }

    override fun handleConfig(config: DetailConfig) {
        config.characterName?.let { mView?.setName(it) }
    }


    override fun handleCharacter(character: Character?) {

        character?.name?.let { mView?.setName(it) }

        character?.thumbnail?.let { mView?.setCover(it) }

        val comics = character?.comics?.items
        if (comics == null || comics.isEmpty()) {
            mView?.hideComics()
        } else {
            mView?.showComics(comics)
        }

        val series = character?.series?.items
        if (series == null || series.isEmpty()) {
            mView?.hideSeries()
        } else {
            mView?.showSeries(series)
        }

        val stories = character?.stories?.items
        if (stories == null || stories.isEmpty()) {
            mView?.hideStories()
        } else {
            mView?.showStories(stories)
        }

        var hasWikiLink = false
        var hasDetailLink = false
        var hasComicLink = false

        character?.urls?.forEach { url ->
            when (url.type) {
                ApiUrl.TYPE_DETAIL -> {
                    hasDetailLink = true
                    mView?.initDetailAction(url.url)
                }
                ApiUrl.TYPE_WIKI -> {
                    hasWikiLink = true
                    mView?.initWikiAction(url.url)
                }
                ApiUrl.TYPE_COMICLINK -> {
                    hasComicLink = true
                    mView?.initComicsAction(url.url)
                }
            }
        }

        if (!hasWikiLink) mView?.hideWikiAction()
        if (!hasComicLink) mView?.hideComics()
        if (!hasDetailLink) mView?.hideDetailAction()

        val description = character?.description
        val hasDescription = description != null && description.isNotBlank()
        when {
            !hasDescription -> mView?.hideDescription()
            else -> mView?.setDescription(description!!)
        }

    }

    override fun handleError(error: Throwable?) {
        mView?.handleError()
    }
}