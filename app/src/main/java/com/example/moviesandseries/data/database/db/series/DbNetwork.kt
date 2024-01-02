package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.Network

@Entity(tableName = "networks")
data class DbNetwork(
    @PrimaryKey
    var id: Int = 0,
    var logoPath: String = "",
    var name: String = "",
    var originCountry: String = ""
)

fun Network.asDbObject(): DbNetwork = DbNetwork(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

fun DbNetwork.asDomainObject(): Network = Network(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

fun List<DbNetwork>.asDomainObject() = this.map { it.asDomainObject() }
