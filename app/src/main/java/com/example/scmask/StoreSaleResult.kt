package com.example.scmask

data class StoreSaleResult (
    var address : String = "",
    var count: Int = 0,
    var stores: MutableList<Stores>
)

data class Stores (
    var addr: String = "",
    var code: String = "",
    var created_at: String = "",
    var lat: Float = 0.0F,
    var lng: Float = 0.0F,
    var name: String = "",
    var remain_stat: String = "",
    var stock_at: String = "",
    var type: String = ""
){
    fun useStores(bean: StoreBean){
        val storeList : MutableList<Stores> = mutableListOf()
        storeList.addAll(bean.storeList)
        storeList.addAll<Stores>(bean.storeList)
    }
}