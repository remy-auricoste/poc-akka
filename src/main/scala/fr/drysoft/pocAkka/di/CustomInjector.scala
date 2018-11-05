package fr.drysoft.pocAkka.di

import com.google.inject.Guice

import scala.reflect.ClassTag

case class CustomInjector() {
  private val injector = Guice.createInjector(ProdModule())

  def getInstance[T](implicit tag: ClassTag[T]): T = injector.getInstance(tag.runtimeClass.asInstanceOf[Class[T]])
}
