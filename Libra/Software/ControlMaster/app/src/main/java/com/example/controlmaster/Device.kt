import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Device", strict = false)
data class Device(
    @field:Element(name = "ID")
    var id: Int = 0,

    @field:Element(name = "Name")
    var name: String = "",

    @field:Element(name = "Image")
    var image: String = ""
)