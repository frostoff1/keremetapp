package omirzhan.kz.keremetapp.Model

class PostData {
    var title: String? = null
    var price: String? = null
    var compound: String? = null
    var delivery: Boolean? = null
    var type: String? = null
    var imageUrl: String? = null
    var userID: String? = null

    constructor() {

    }

    constructor(title: String, price: String, compound: String,
                delivery: Boolean, type: String, imageUrl: String, userID: String) {
        this.title = title
        this.price = price
        this.compound = compound
        this.delivery = delivery
        this.type = type
        this.imageUrl = imageUrl
        this.userID = userID
    }
}
