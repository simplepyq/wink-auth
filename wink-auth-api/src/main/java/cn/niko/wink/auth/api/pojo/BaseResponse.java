package cn.niko.wink.auth.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author niko.pan
 * @version 1.0.0
 * @since 2020/4/18 18:23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private String code;

    private String msg;

}
