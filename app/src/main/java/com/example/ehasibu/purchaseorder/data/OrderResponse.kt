package com.example.ehasibu.purchaseorder.data

data class OrderResponse<T>(
    val entity: List<T>,
    val message: String,
    val statusCode: Int
) : List<OrdersEntity> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: OrdersEntity): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<OrdersEntity>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): OrdersEntity {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: OrdersEntity): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<OrdersEntity> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: OrdersEntity): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<OrdersEntity> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<OrdersEntity> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<OrdersEntity> {
        TODO("Not yet implemented")
    }
}