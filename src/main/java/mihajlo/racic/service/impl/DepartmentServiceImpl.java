package mihajlo.racic.service.impl;

import java.util.List;
import java.util.Optional;
import mihajlo.racic.domain.Department;
import mihajlo.racic.repository.DepartmentRepository;
import mihajlo.racic.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Department}.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        log.debug("Request to save Department : {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> partialUpdate(Department department) {
        log.debug("Request to partially update Department : {}", department);

        return departmentRepository
            .findById(department.getId())
            .map(existingDepartment -> {
                if (department.getDepartmentName() != null) {
                    existingDepartment.setDepartmentName(department.getDepartmentName());
                }

                return existingDepartment;
            })
            .map(departmentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        log.debug("Request to get all Departments");
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findOne(Long id) {
        log.debug("Request to get Department : {}", id);
        return departmentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Department : {}", id);
        departmentRepository.deleteById(id);
    }
}
