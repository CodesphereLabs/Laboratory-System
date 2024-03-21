/**
 * Author : rasintha_j
 * Date : 3/21/2024
 * Time : 10:21 AM
 * Project Name : laboratory
 */

package com.laboratory.model.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ResponseBean {
    String responseCode;
    String responseMsg;
    Object content;
}
