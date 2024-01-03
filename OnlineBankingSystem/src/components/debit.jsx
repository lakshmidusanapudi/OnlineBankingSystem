import React from "react";
import axios from "axios";
import base_url from "../api";
import "../style/send_money.css";

function Debit(){

    const [transaction, setTransaction]= React.useState({});

    function formHandler(e){
        
        axios.post(`${base_url}/transaction/debit`, transaction)
          .then((res) => {
            const result=res.data;
            
            if(result==="Debited"){

             alert("Transaction Successful");
            }
            else if(res.data==="Insufficient balance")
            {
                alert("Insufficient balance");
            }
            else{
                alert("Account not Found");
            }
           })
          .catch((err) => {
            
            console.log("");
          });
    
        e.preventDefault();
    }

    return (
    <div id="send-money-container">

   <form autoComplete="off" id="send-money-form" onSubmit={formHandler}>
       <input onChange={(e)=>{
           setTransaction({...transaction, id:e.target.value})
       }} id="id" type="number" placeholder="Account number " required name="id"/>
       <input onChange={(e)=>{
           setTransaction({...transaction, balance:e.target.value})
       }} type="number" placeholder="Amount " required name="balance"/>
       <button type="submit" value="SEND">Transfer</button>
   </form>
    </div>);
}

export default Debit;