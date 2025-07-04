package forms4s.tyrian

import forms4s.{FormElementState, FormElementUpdate}
import tyrian.Html

/** Abstraction for rendering form elements with Tyrian */
trait FormRenderer {

  def renderElement(state: FormElementState): Html[FormElementUpdate] = state match {
    case text: FormElementState.Text         => renderTextInput(text)
    case select: FormElementState.Select     => renderSelect(select)
    case checkbox: FormElementState.Checkbox => renderCheckbox(checkbox)
    case subform: FormElementState.Group     => renderGroup(subform)
    case number: FormElementState.Number     => renderNumberInput(number)
    case number: FormElementState.Multivalue => renderMultivalue(number)
    case state: FormElementState.Time        => renderTime(state)
    case state: FormElementState.Date        => renderDate(state)
    case state: FormElementState.DateTime    => renderDateTime(state)
  }

  protected def renderTextInput(state: FormElementState.Text): Html[FormElementUpdate]

  protected def renderNumberInput(state: FormElementState.Number): Html[FormElementUpdate]

  protected def renderSelect(state: FormElementState.Select): Html[FormElementUpdate]

  protected def renderCheckbox(state: FormElementState.Checkbox): Html[FormElementUpdate]

  protected def renderDate(state: FormElementState.Date): Html[FormElementUpdate]
  protected def renderTime(state: FormElementState.Time): Html[FormElementUpdate]
  protected def renderDateTime(state: FormElementState.DateTime): Html[FormElementUpdate]

  protected def renderGroup(state: FormElementState.Group): Html[FormElementUpdate]

  protected def renderMultivalue(state: FormElementState.Multivalue): Html[FormElementUpdate]
}

object FormRenderer {

  case class Path(segments: List[String]) {
    def /(segment: String): Path = Path(segment :: segments)
    def asString: String         = segments.mkString(".")
  }

}
