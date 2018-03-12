package xyz.dokup.sharedelementtransitionsample.list

import java.io.Serializable

/**
 * Created by e10dokup on 2018/03/11.
 */
data class Item constructor(
        var id: Int,
        var name: String
): Serializable