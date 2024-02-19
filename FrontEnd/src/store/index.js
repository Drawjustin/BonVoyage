import { configureStore } from "@reduxjs/toolkit"
import categoriesSlice from "./categories/categories.slice"
import productsSlice from './products/products.slice';
import productSlice from './products/product.slice';
import cartSlice from './cart/cart.slice';
import orderSlice from './order/order.slice'
import userSlice from './user/user.slice'
import artistsSlice from "./Artists/artist.slice";
import { useSelector } from "react-redux";

const store = configureStore({
    reducer: {
        orderSlice,
        categoriesSlice,
        productSlice,
        productsSlice,
        cartSlice,
        userSlice,
        artistsSlice
    }
})

export default store