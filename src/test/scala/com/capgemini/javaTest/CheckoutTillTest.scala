package com.capgemini.javaTest

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  * Created by Bharathi Pairan on 13/03/2018.
  */
@RunWith(classOf[JUnitRunner])
class CheckoutTillTest extends FlatSpec with Matchers with BeforeAndAfterAll {

    "Total cost" should "be calculated for apples" in {
        CheckoutTill.totalCost("Apple, Apple, Apple") shouldBe 1.80
    }

    it should "be calculated for oranges" in {
        CheckoutTill.totalCost("Orange, Orange, Orange, Orange, Orange") shouldBe 1.25

    }

    it should "be calculated for oranges and apples" in {
        CheckoutTill.totalCost("Apple, Apple, Orange, Apple") shouldBe 2.05
    }

    "Unknown item at till" should "be ignored" in {
        CheckoutTill.totalCost("Apple, Apple, Orange, Apple, Strawberry") shouldBe 2.05
        CheckoutTill.totalCost("Strawberry") shouldBe 0.0
    }

    "Empty input" should "return 0 total cost" in {
        CheckoutTill.totalCost("") shouldBe 0.0
    }

    "Null input" should "return 0 total cost" in {
        CheckoutTill.totalCost(null) shouldBe 0.0
    }


    "Number of quantities to be applied for pricing" should "be calculated for Buy one get one free with even quantity" in {
        CheckoutTill.calculateOfferQuantity(quantity = 6, noOfItemsInOffer = 2, quantitiesMultiplier = 1) shouldBe 3

    }

    it should "be calculated for Buy one get one free with odd quantity" in {
        CheckoutTill.calculateOfferQuantity(quantity = 7, noOfItemsInOffer = 2, quantitiesMultiplier = 1) shouldBe 4

    }

    it should "be calculated for 3 for 2 offer with even quantity" in {
        CheckoutTill.calculateOfferQuantity(quantity = 7, noOfItemsInOffer = 3, quantitiesMultiplier = 2) shouldBe 5

    }

    "Discounted total cost" should "be calculated for Buy one get one free on applies"  in {
        CheckoutTill.totalCost("Apple, Apple, Apple", CheckoutTill.withOffer) shouldBe 1.20
    }

    it should "be calculated for 3 for 2 offer on oranges" in {
        CheckoutTill.totalCost("Orange, Orange, Orange, Orange, Orange", CheckoutTill.withOffer) shouldBe 1.00
    }


}
