package com.appsfactory.musicmgmt.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.appsfactory.musicmgmt.data.local.dao.AlbumDao
import com.appsfactory.musicmgmt.data.local.dao.AlbumDatabase
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException


class RepositoryTest {

    private lateinit var db: AlbumDatabase
    private lateinit var albumDao: AlbumDao
    private lateinit var album: AlbumEntity

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, AlbumDatabase::class.java
        ).build()
        albumDao = db.albumDao()

        album = AlbumEntity(
            "cher",
            "test.jpg",
            null,
            "Believe",
            "100", "www.google.com", null, 1
        )
    }

    @Test
    @Throws(Exception::class)
    fun writeAlbumAndReadInList() = runBlocking {

        albumDao.insertAlbum(album)
        val albumList = albumDao.getAllAlbums().first()
        assertEquals(albumList[0].name, album.name)
    }

    @Test
    @Throws(Exception::class)
    fun getAlbumWithId() = runBlocking {

        albumDao.insertAlbum(album)
        val albumModel = albumDao.getAlbumWithId(1)
        assertEquals(albumModel.name, album.name)
    }

    @Test
    @Throws(Exception::class)
    fun getAlbumId() = runBlocking {

        albumDao.insertAlbum(album)
        val albumId = albumDao.getAlbumId("Believe", "cher")
        assertEquals(albumId, album.id)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}