package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.Network

/**
 * Represents a database entity for the "networks" table, storing information about series networks.
 *
 * @property id The unique identifier for the network (primary key).
 * @property logoPath The path to the logo associated with the network.
 * @property name The name of the network.
 * @property originCountry The country of origin for the network.
 */
@Entity(tableName = "networks")
data class DbNetwork(
    @PrimaryKey
    var id: Int = 0,
    var logoPath: String = "",
    var name: String = "",
    var originCountry: String = ""
)

/**
 * Extension function to convert a [Network] domain object to a [DbNetwork] database object.
 *
 * @return The corresponding [DbNetwork] object.
 */
fun Network.asDbObject(): DbNetwork = DbNetwork(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

/**
 * Extension function to convert a [DbNetwork] database object to a [Network] domain object.
 *
 * @return The corresponding [Network] object.
 */
fun DbNetwork.asDomainObject(): Network = Network(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

/**
 * Extension function to convert a list of [DbNetwork] database objects to a list of [Network] domain objects.
 *
 * @return The corresponding list of [Network] objects.
 */
fun List<DbNetwork>.asDomainObject() = this.map { it.asDomainObject() }
