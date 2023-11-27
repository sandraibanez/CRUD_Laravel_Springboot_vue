import Api from "../Api";
import secrets from "../../secrets";

export default {
    
    FormatFilters(params) {
        let params_ = [];
        Object.entries(params).forEach(item => {
            if (item[0] === 'categories' && item[1].length > 0) {
                const categories = item[1].map(item => `categories=${item}`).join('&');
                params_.push(categories)
            } else if (item[1] != null) {
                params_.push(`${item[0]}=${item[1]}`);
            }
        });
        return params_.join('&')
    },
    GetMesas(params) {
        console.log(params);
        return Api(secrets.URL_SPRING).get(`mesa?${this.FormatFilters(params)}`);
    },//GetMesas
    // GetMesas() {
    //     return Api(secrets.URL_SPRING).get('mesa');
    // },//GetMesas
    GetOneMesa(id) {
        return Api(secrets.URL_SPRING).get('mesa/' + id);
    },//GetOneLinkMesa

}//export