package org.littlewings.javaee7.typed

import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Typed

class Book

trait Shop[T]

class Business

@Typed(Array(classOf[Shop[Book]]))
@ApplicationScoped
class BookShop extends Business with Shop[Book]
