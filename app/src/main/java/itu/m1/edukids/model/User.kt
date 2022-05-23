package itu.m1.edukids.model

import com.squareup.moshi.JsonClass
import java.util.Date

data class User(
    var login : String,
    var password : String
){
    var prenom : String = ""
    var nom : String = ""
    var email : String = ""
    var date_naissance : String = ""
    var date_inscription :  String = ""

    constructor(prenom : String, nom :String, email:String,date_naissance:String,date_inscription:String,login:String,password:String) : this(login,password){
        this.prenom = prenom
        this.nom = nom
        this.email = email
        this.date_naissance = date_naissance
        this.date_inscription = date_inscription
        this.login = login
        this.password = password
    }
}
