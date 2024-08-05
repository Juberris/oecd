package cl.sii.filepackaging.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppProp {
    String pwd;
    String cert;
    String uri;
    String usr;
    String sender;
    String inbox;
    String outbox;
    String keystorePrePath;
    String keystorePwd;
    String keyPwd;
    String keyLength;
    String contact;

}
