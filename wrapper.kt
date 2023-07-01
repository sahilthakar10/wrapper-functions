package com.kotlin.lamda

data class PostResponse(var postName: String)

fun main() {

    apiFlow<PostResponse> { wrap ->

        wrap.success { postResponse ->
            println(postResponse.postName)
        }

    }

}

fun <T> apiFlow(init: (Wrapper<T>) -> Unit) {

    val wrap = Wrapper<T>()
    init.invoke(wrap)
    call(wrap)
}

fun <T> call(wrap: Wrapper<T>) {
    wrap.success(createPostResponse() as T)
}

fun createPostResponse(): PostResponse {
    return PostResponse("Sahil")
}

class Wrapper<T> {

    var success : (T) -> Unit = {}

    fun success(onSuccess: (T) -> Unit) {
        success = onSuccess
    }

}
