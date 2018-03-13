package com.capgemini.javaTest

import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

/**
  * Created by Bharathi Pairan on 13/03/2018.
  */
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

}
