package fr.drysoft.pocAkka.di

import com.google.inject.{ Guice, Module }

import scala.reflect.ClassTag

case class CustomInjector(module: Module = new ProdModule) {
  private val injector = Guice.createInjector(module)

  def getInstance[T](implicit tag: ClassTag[T]): T = injector.getInstance(tag.runtimeClass.asInstanceOf[Class[T]])
}
