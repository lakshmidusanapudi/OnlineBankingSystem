import React from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import base_url from "../api";
import "../style/customers.css";
import LeftArrow from '@mui/icons-material/ArrowBack';
import SendIcon from '@mui/icons-material/ArrowCircleLeftTwoTone';
import ReceiveIcon from '@mui/icons-material/ArrowCircleRightTwoTone'; 
function Customer(props){

  function deleteUser(id) {
        axios.delete(`${base_url}/delete/${id}`).then((res)=>{
            alert("Beneficiary Deleted");
        }, (err)=>{
            console.log("Unable to delete");
        });
    }

    const isSendIcon = true;

    return (
    <tr>
        <td>{props.id}</td>
        <td>{props.name}</td>
        <td>{props.email}</td>
        <td>{props.mobile}</td>
        <td>₹ {props.balance}</td>
        <td className="actions">

       <button className="list-btn delete" onClick={()=>deleteUser(props.id)}><i class="fas fa-trash-alt"></i></button>
      <Link  to={`/update?id=${props.id}`}><button className="list-btn edit"><i class="fas fa-user-edit"></i></button></Link>
        <Link to={`/transaction/debit?id=${props.id}`}><button className="list-btn send">
            {isSendIcon ? <ReceiveIcon /> : <LeftArrow />}
            </button>
        </Link>

        <Link to={`/transfer?id=${props.id}`}><button className="list-btn send"> {isSendIcon ? <SendIcon /> : <LeftArrow />}</button>
        </Link>

        </td>
    </tr>);
}

export default Customer;