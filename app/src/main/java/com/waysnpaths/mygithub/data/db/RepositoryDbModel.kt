package com.waysnpaths.mygithub.data.db

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel



@Table(database = MyDatabase::class)
class RepositoryDbModel : BaseModel() {

    @Column
    @PrimaryKey
    var id: Long = 0

    @Column
    var name: String? = null

    @Column
    var description: String? = null
}