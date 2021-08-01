package cc.niushuai.zuaenrollmonitor.util;

import cc.niushuai.zuaenrollmonitor.entity.KuaiDiEntity;
import cn.hutool.core.date.DateTime;
import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.stream.Collectors;

public class KudiDiUtil {

    // 9865660291832
    public static String getEms(String postNumber) {

        GlobalHeaders.INSTANCE.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        String res = HttpUtil.get("https://express.baidu.com/express/api/express?tokenV2=P8kojfXfd67esr1rEk2lFMeZaUbgMmGjm__vF6gNbkqX1FC6C_7WW0Y4-DOZ9kMB&com=ems&nu=" + postNumber);
        res = HttpUtil.get("https://express.baidu.com/express/api/express?tokenV2=P8kojfXfd67esr1rEk2lFMeZaUbgMmGjm__vF6gNbkqX1FC6C_7WW0Y4-DOZ9kMB&com=ems&nu=" + postNumber);

        KuaiDiEntity kuaiDiEntity = JSONUtil.toBean(res, KuaiDiEntity.class);

        List<KuaiDiEntity.DataDTO.InfoDTO.ContextDTO> context = kuaiDiEntity.getData().getInfo().getContext();

        context = context.stream().sorted((x, y) -> Long.compare(Long.valueOf(x.getTime()), Long.parseLong(y.getTime()))).collect(Collectors.toList());

        StringBuffer buffer = new StringBuffer();
        buffer.append(System.lineSeparator());
        for (KuaiDiEntity.DataDTO.InfoDTO.ContextDTO dto : context) {
            buffer.append(DateTime.of(Long.valueOf(dto.getTime()) * 1000L))
                    .append(System.lineSeparator())
                    .append(">>> ")
                    .append(dto.getDesc())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        }
        return buffer.toString();
    }

    public static void main(String[] args) {

        getEms("");
    }
}
