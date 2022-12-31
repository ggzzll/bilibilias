package com.imcys.bilibilias.base.api


class BilibiliApi() {

    companion object {
        /**
         * 常见注解
         * mid为UID
         *
         */

        val serviceApi: String = "https://api.bilibili.com/"
        val loginApi: String = "http://passport.bilibili.com/"


        val getLoginQRPath: String = loginApi + "x/passport-login/web/qrcode/generate"

        //需要登陆密钥
        val getLoginStatePath: String = loginApi + "x/passport-login/web/qrcode/poll"

        //需要传入PS为展示项
        val homeRCMDVideoPath: String = serviceApi + "x/web-interface/index/top/feed/rcmd"

        //热门视频
        //get，ps和pn
        val homePopularVideoPath: String = serviceApi + "x/web-interface/popular"

        //需要传入mid
        val getUserInfoPath: String = serviceApi + "x/space/acc/info"

        //获取个人基本信息
        val getMyUserData = serviceApi + "x/member/web/account"

        //获取用户卡片信息
        val getUserCardPath = serviceApi + "x/web-interface/card"

        //post aid bvid like:1,2 csrf:j_xx
        val likeVideoPath = serviceApi + "x/web-interface/archive/like"

        //获取视频详细信息 get bvid avid
        val getVideoDataPath = serviceApi + "x/web-interface/view"


        //对视频进行点赞
        val videLikePath = serviceApi + "x/web-interface/archive/like"

        //对视频进行投币
        val videAddCoinPath = serviceApi + "x/web-interface/coin/add"

        //获取收藏列表
        val userCreatedScFolderPath = serviceApi + "x/v3/fav/folder/created/list-all"

        //收藏夹详细内容
        val userCollectionDataPath = serviceApi + "x/v3/fav/resource/list"

        val videoPlayPath = serviceApi + "x/player/playurl"
        val bangumiPlayPath = serviceApi + "pgc/player/web/playurl"

        //弹幕下载
        val videoDanMuPath = serviceApi + "x/v1/dm/list.so"

        //视频列表
        val videoPageListPath = serviceApi + "x/player/pagelist"

        //修改视频收藏
        val videoCollectionSetPath = serviceApi + "x/v3/fav/resource/deal"

        //投币地址
        val videoCoinAddPath = serviceApi + "x/web-interface/coin/add"

        //获取剧集明细 get season_id / ep_id
        val bangumiVideoDataPath = serviceApi + "pgc/view/web/season"

        //获取用户基本信息
        val userBaseDataPath = serviceApi + "x/space/acc/info"

        //用户状态
        val userUpStat = serviceApi + "x/space/upstat"

        //用户投稿
        val userWorksPath = serviceApi + "x/space/arc/search"

        //播放历史
        val userPlayHistoryPath = serviceApi + "x/web-interface/history/cursor"
    }

}
