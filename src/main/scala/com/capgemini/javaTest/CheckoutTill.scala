package com.capgemini.javaTest

import com.capgemini.javaTest.Items._

/**
  * Created by Bharathi Pairan on 13/03/2018.
  */
object CheckoutTill {


    def calculateOfferQuantity(quantity: Int, noOfItemsInOffer: Int, quantitiesMultiplier: Int): Int = {
        val reminder = quantity % noOfItemsInOffer

        val quantitiesInOffer = (quantity - reminder) / noOfItemsInOffer

        (quantitiesInOffer * quantitiesMultiplier) + reminder
    }

    val buyOneGetOneFree: Int => Int = (quantity: Int) => calculateOfferQuantity(quantity, 2, 1)

    val threeForTwo: Int => Int = (quantity: Int) => calculateOfferQuantity(quantity, 3, 2)

    val withOffer: PartialFunction[CheckoutItem, CheckoutItem] = {
        case checkoutItem =>
            val calculatedQty = checkoutItem.storeItem match {
                case Apple => buyOneGetOneFree(checkoutItem.quantity)
                case Orange => threeForTwo(checkoutItem.quantity)
                case _ => checkoutItem.quantity
            }
            checkoutItem.copy(quantity = calculatedQty)
    }

    val withoutOffer: PartialFunction[CheckoutItem, CheckoutItem] = {
        case checkoutItem => checkoutItem
    }


    /**
      * Assumes the input to be comma(,) separated string, which is converted to the equivalent case object to find the price
      * All StoreItem are then grouped based on decimal precision, summed and final value is divided by 10 pow precision value to obtain the actual cost
      * this guarantees floating point precision is avoided
      *
      * @param items comma separated items in available in the store
      * @return total cost of all items in the input, unknown items are ignored
      */
    def totalCost(items: String, applyOffer: PartialFunction[CheckoutItem, CheckoutItem] = withoutOffer): Double = {
        Option(items).getOrElse("").split(",")
            .map(toStoreItem)
            .groupBy(identity)
            .mapValues(_.length)
            .map { case (storeItem, quantity) => CheckoutItem(storeItem, quantity) }
            .map(applyOffer)
            .map(checkoutItem => checkoutItem.cost)
            .sum
    }

    /**
      * Maps the string to one of the StoreItem
      *
      * @param item item as string
      * @return sub type of StoreItem
      */
    def toStoreItem(item: String): StoreItem = item.trim match {
        case "Apple" => Apple
        case "Orange" => Orange
        case _ => Unknown
    }

}
