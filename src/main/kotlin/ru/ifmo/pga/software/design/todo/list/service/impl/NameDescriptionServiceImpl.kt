package ru.ifmo.pga.software.design.todo.list.service.impl

import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.ifmo.pga.software.design.todo.list.dao.NameDescriptionDao
import ru.ifmo.pga.software.design.todo.list.entity.NameDescriptionEntity
import ru.ifmo.pga.software.design.todo.list.service.NameDescriptionService

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
abstract class NameDescriptionServiceImpl<T : NameDescriptionEntity, DAO : NameDescriptionDao<T>> :
    ServiceImpl<T, DAO>(), NameDescriptionService<T> {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    override fun findByName(name: String): List<T> {
        return invokeDaoMethod { dao.findByName(name) }
    }
}
