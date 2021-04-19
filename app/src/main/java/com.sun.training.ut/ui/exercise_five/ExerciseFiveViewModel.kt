package com.sun.training.ut.ui.exercise_five

import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.Constant.DEFAULT_PRICE
import com.sun.training.ut.data.model.Pizza
import com.sun.training.ut.ui.base.BaseViewModel

class ExerciseFiveViewModel : BaseViewModel() {
    private var isDelivery: Boolean = false
    private var isVoucher: Boolean = false

    var totalPrice = 0
    val discountLiveData: MutableLiveData<String> = MutableLiveData()

    fun calculateCouponWithPizza() {
        if (totalPrice > 0) {
            val typeDelivery =
                if (isDelivery) Constant.TypeDelivery.DELIVERY else Constant.TypeDelivery.RECEIVE_AT_STORE
            val pizza = Pizza(
                bill = totalPrice,
                typeDelivery = typeDelivery,
                isCoupon = isVoucher
            )
            var discount = ""

            if (pizza.bill > DEFAULT_PRICE) {
                discount += Constant.Coupon.POTATO_PROMOTION.coupon
            }

            discount += if (pizza.typeDelivery == Constant.TypeDelivery.DELIVERY) {
                if (pizza.isCoupon) {
                    Constant.Coupon.OFF_20.coupon
                } else {
                    Constant.Coupon.REGULAR_FEE.coupon
                }
            } else {
                Constant.Coupon.PIZZA_SECOND_FREE.coupon
            }
            discountLiveData.postValue(discount)
        } else {
            discountLiveData.postValue("")
        }
    }

    fun onChangedDelivery(isChecked: Boolean) {
        isDelivery = isChecked
    }

    fun onChangedVoucher(isChecked: Boolean) {
        isVoucher = isChecked
    }

}