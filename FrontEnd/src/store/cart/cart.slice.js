import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import LocalStorage from '@/constants'


export const postOrder = createAsyncThunk(
    "cart/postOrder",
    async (order, thunkAPI) => {
        try {
            await axios.post(
                "https://640f6d494ed25579dc4ec41b.mockapi.io/orders",
                order
            )

            thunkAPI.dispatch(sendOrder())
        } catch (error) {
            return thunkAPI.rejectWithValue("Error sending order");
        }
    }
)


const initialState = {
    products: LocalStorage.getItem("cartProducts") ?
        JSON.parse(LocalStorage.getItem("cartProducts") || "") : [],
    totalPrice: 0,
    userId: LocalStorage.getItem("userId") ?
        JSON.parse(LocalStorage.getItem("userId") || "") : "",
}


export const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        setUserId: (state, action) => {
            state.userId = action.payload;

            LocalStorage.setItem('userId', JSON.stringify(state.userId));
        },
        removeUserId: (state) => {
            state.userId = "";

            LocalStorage.setItem('userId', JSON.stringify(state.userId));
        },
        addToCart: (state, action) => {
            state.products.push({
                ...action.payload,
                quantity: 1,
                total: action.payload.price
            })

            LocalStorage.setItem('cartProducts', JSON.stringify(state.products));
        },
        deleteFromCart: (state, action) => {
            state.products = state.products.filter((item) => item.id !== action.payload)

            LocalStorage.setItem('cartProducts', JSON.stringify(state.products));
        },
        incrementProduct: (state, action) => {
            state.products = state.products.map((item) =>
                item.id === action.payload
                    ? {
                        ...item,
                        quantity: item.quantity + 1,
                        total: item.price * (item.quantity + 1)
                    }
                    : item
            )
            console.log(state.products);
            LocalStorage.setItem('cartProducts', JSON.stringify(state.products));
        },

        decrementProduct: (state, action) => {
            state.products = state.products.map((item) =>
                item.id === action.payload
                    ? {
                        ...item,
                        quantity: item.quantity - 1,
                        total: item.price * (item.quantity - 1)
                    }
                    : item
            )
            LocalStorage.setItem('cartProducts', JSON.stringify(state.products));
        },
        getTotalPrice: (state) => {
            state.totalPrice = state.products.reduce(
                (acc, item) => (acc += item.total),
                0
            )

        },
        sendOrder: (state) => {
            state.products = [];
            LocalStorage.setItem("cartProducts", JSON.stringify(state.products));
        },
    }
})

export const {
    addToCart,
    sendOrder,
    deleteFromCart,
    incrementProduct,
    decrementProduct,
    getTotalPrice,
    setUserId,
    removeUserId
} = cartSlice.actions;

export default cartSlice.reducer;