package itu.m1.edukids.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import itu.m1.edukids.utils.MySQLiteOpenHelper
import java.sql.Date
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class LocalAccess(contexte : Context) {
    private val nomBase: String = "bdCoach.sqlite"
    private val versionBase: Int = 1
    private val accesBD: MySQLiteOpenHelper =
        MySQLiteOpenHelper(contexte, nomBase, null, versionBase)
    private lateinit var bd: SQLiteDatabase

    fun ajout(user : User){
        with(user){
            bd = accesBD.writableDatabase
            var req = String.format("insert into user (prenom,nom,email,date_naissance,date_inscription,login,password) values "+
                "(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\" ,\"%s\")", prenom, nom, email, date_naissance, date_inscription, login, password)
            bd.execSQL(req)
        }
    }

    fun recupDernier() : User?{
        bd = accesBD.readableDatabase
        var user: User? = null
        val req = "select * from user"
        val curseur: Cursor = bd.rawQuery(req, null)
        curseur.moveToLast()
        if(!curseur.isAfterLast) {
            with(curseur) {
                val prenom = getString(1)
                val nom = getString(2)
                val email = getString(3)
                var date_naissance = getString(4)
                var date_inscription = getString(5)
                val login = getString(6)
//                user = User(login, password)
                user = User(prenom, nom,email, date_naissance, date_inscription,login,"")
            }
        }
        curseur.close()
        return user
    }

    fun deconnexion(){
        bd = accesBD.readableDatabase
        var req = "delete from user"
        bd.execSQL(req)
    }
}