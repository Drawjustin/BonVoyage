'use client'
import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import myart from './first_product.jpg';

const MyArt = () => {

    return (
        <div style={{ display:'flex', justifyContent:'space-between' }}>
            <div className="card" style={{ width: '22rem', height: '26rem', alignItems:'center' }}>
            <img src={myart} className="card-img-top" style={{ width:'200px', height: '200px'}} alt="..." />
            <div className="card-body">
                <h5 className="card-title">Card title</h5>
                <p className="card-text">
                Some quick example text to build on the card title and make up the bulk of the card's content.
                </p>
                <a href="#" className="btn btn-primary">
                Go somewhere
                </a>
            </div>
            </div>
            <div className="card"  style={{ width: '22rem', height: '26rem', alignItems:'center' }}>
            <img src={myart} className="card-img-top"  style={{ width:'200px', height: '200px'}} alt="..." />
            <div className="card-body">
                <h5 className="card-title">Card title</h5>
                <p className="card-text">
                Some quick example text to build on the card title and make up the bulk of the card's content.
                </p>
                <a href="#" className="btn btn-primary">
                Go somewhere
                </a>
            </div>
            </div>
        </div>
    )
}

export default MyArt